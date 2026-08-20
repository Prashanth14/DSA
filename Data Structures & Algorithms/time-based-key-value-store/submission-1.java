/*
 * Pattern:
 * HashMap + Sorted List per Key + Binary Search
 *
 * Goal:
 * Store multiple values for the same key at different timestamps,
 * then retrieve the value associated with the LARGEST timestamp
 * such that:
 *
 *      storedTimestamp <= requestedTimestamp
 *
 *
 * Example:
 *
 * set("alice", "happy", 1)
 * set("alice", "sad",   3)
 *
 * Stored for "alice":
 *
 * [(1, "happy"), (3, "sad")]
 *
 * get("alice", 2)
 *
 * Valid timestamps <= 2:
 * [1]
 *
 * So return:
 * "happy"
 *
 *
 * IMPORTANT OBSERVATION:
 *
 * The problem guarantees that timestamps passed to set()
 * are strictly increasing.
 *
 * Therefore, for each key, values are automatically stored
 * in sorted timestamp order.
 *
 * That means:
 *
 * set() -> simply append
 * get() -> binary search
 *
 *
 * Binary Search Pattern:
 *
 * We are NOT searching for an exact timestamp.
 *
 * We want:
 *
 *      RIGHTMOST timestamp <= target
 *
 * Example:
 *
 * timestamps = [1, 3, 7, 10]
 * target = 8
 *
 * Valid timestamps:
 * 1, 3, 7
 *
 * We want the largest valid one:
 * 7
 *
 *
 * During binary search:
 *
 * if currentTimestamp <= target:
 *
 *      current value is a valid candidate
 *      save its index
 *
 *      BUT there might be a larger valid timestamp
 *      further to the right
 *
 *      so:
 *          left = mid + 1
 *
 * else:
 *
 *      current timestamp is too large
 *
 *      so:
 *          right = mid - 1
 *
 *
 * Complexity:
 *
 * Let m = number of timestamps stored for a specific key.
 * Let N = total number of set() calls overall.
 *
 * set():
 *      O(1) amortized
 *
 *      HashMap lookup -> O(1) average
 *      ArrayList append -> O(1) amortized
 *
 * get():
 *      O(log m)
 *
 *      HashMap lookup -> O(1) average
 *      Binary search over that key's timestamp list -> O(log m)
 *
 * Space:
 *      O(N)
 *
 *      Every set() call stores one TimeStampedValue object.
 */


/*
 * Stores one value together with the timestamp
 * at which it was assigned.
 *
 * Example:
 *
 * value = "happy"
 * timeStamp = 1
 */
class TimeStampedValue {

    String value;
    int timeStamp;

    public TimeStampedValue(String value, int timeStamp) {
        this.value = value;
        this.timeStamp = timeStamp;
    }
}


class TimeMap {

    /*
     * key ->
     * list of (value, timestamp) pairs for that key.
     *
     * Example:
     *
     * "alice" ->
     * [(happy, 1), (sad, 3), (excited, 7)]
     *
     * Since timestamps arrive in strictly increasing order,
     * every ArrayList stays sorted by timestamp automatically.
     */
    Map<String, ArrayList<TimeStampedValue>> map;


    public TimeMap() {
        map = new HashMap<>();
    }


    public void set(String key, String value, int timestamp) {

        /*
         * If this key has never been seen before,
         * create an empty list for it.
         */
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<>());
        }

        /*
         * Retrieve this key's timestamp/value history.
         */
        ArrayList<TimeStampedValue> timeStampedValues = map.get(key);

        /*
         * Append the new value.
         *
         * We do NOT need to sort because timestamps from set()
         * are guaranteed to arrive in strictly increasing order.
         *
         * Therefore:
         * set() is O(1) amortized.
         */
        timeStampedValues.add(
            new TimeStampedValue(value, timestamp)
        );
    }


    public String get(String key, int timestamp) {

        /*
         * If the key was never stored,
         * there cannot be any valid value.
         */
        if (!map.containsKey(key)) {
            return "";
        }

        /*
         * Get all timestamp/value pairs for this key.
         *
         * This list is already sorted by timestamp.
         */
        ArrayList<TimeStampedValue> timeStampedValues = map.get(key);

        /*
         * Binary search for:
         *
         * RIGHTMOST timestamp <= requested timestamp
         *
         * Optional means:
         *
         * there may be a TimeStampedValue,
         * or there may be no valid value.
         */
        Optional<TimeStampedValue> timeStamp =
            binarySearchTimeStamp(timeStampedValues, timestamp);


        /*
         * No stored timestamp was <= requested timestamp.
         *
         * Example:
         *
         * stored timestamps = [3, 5, 8]
         * requested timestamp = 2
         *
         * No valid answer.
         */
        if (timeStamp.isEmpty()) {
            return "";
        }


        /*
         * Optional contains a valid TimeStampedValue.
         *
         * Return only its stored value.
         */
        return timeStamp.get().value;
    }


    /*
     * Binary Search Goal:
     *
     * Find the RIGHTMOST element whose:
     *
     *      timeStamp <= target
     *
     * Example:
     *
     * arr timestamps:
     * [1, 3, 7, 10]
     *
     * target = 8
     *
     * Answer should be timestamp 7.
     *
     *
     * TC: O(log m)
     * SC: O(1)
     *
     * m = number of entries for this specific key.
     */
    public Optional<TimeStampedValue> binarySearchTimeStamp(
        ArrayList<TimeStampedValue> arr,
        int target
    ) {

        int left = 0;
        int right = arr.size() - 1;

        /*
         * Stores the best valid index found so far.
         *
         * -1 means:
         * we have not yet found any timestamp <= target.
         */
        int matchIndex = -1;


        while (left <= right) {

            // Overflow-safe middle index.
            int mid = left + (right - left) / 2;

            TimeStampedValue cur = arr.get(mid);


            if (cur.timeStamp <= target) {

                /*
                 * Current timestamp is VALID.
                 *
                 * Save it as our best answer so far.
                 */
                matchIndex = mid;

                /*
                 * But we want the LARGEST valid timestamp.
                 *
                 * There may be another valid timestamp
                 * further to the right.
                 *
                 * So continue searching right.
                 */
                left = mid + 1;

            } else {

                /*
                 * Current timestamp is greater than target.
                 *
                 * It is invalid, and everything to its right
                 * is also too large because the list is sorted.
                 *
                 * Search left.
                 */
                right = mid - 1;
            }
        }


        /*
         * No timestamp <= target was found.
         */
        if (matchIndex == -1) {
            return Optional.empty();
        }


        /*
         * Return the rightmost valid timestamp/value pair.
         */
        return Optional.of(arr.get(matchIndex));
    }
}