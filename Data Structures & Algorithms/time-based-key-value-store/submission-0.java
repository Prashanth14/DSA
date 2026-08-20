class TimeStampedValue{
    String value;
    int timeStamp;

    public TimeStampedValue(String value, int timeStamp){
        this.value = value;
        this.timeStamp = timeStamp;
    } 
}

class TimeMap {
    Map<String, ArrayList<TimeStampedValue>> map;

    public TimeMap() {
        map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        if(!map.containsKey(key)){
            map.put(key, new ArrayList<>());
        }

        ArrayList<TimeStampedValue> timeStampedValues = map.get(key);
        timeStampedValues.add(new TimeStampedValue(value, timestamp));
    }
    
    public String get(String key, int timestamp) {
        if(!map.containsKey(key)){
            return "";
        }

        ArrayList<TimeStampedValue> timeStampedValues = map.get(key);
        Optional<TimeStampedValue> timeStamp = binarySearchTimeStamp(timeStampedValues, timestamp);

        if(timeStamp.isEmpty()){
            return "";
        }

        return timeStamp.get().value;

    }

    public Optional<TimeStampedValue> binarySearchTimeStamp( ArrayList<TimeStampedValue> arr, int target){
        int left = 0, right = arr.size()-1;
        int matchIndex = -1;

        while(left <= right){
            int mid = left + (right - left)/2;
            TimeStampedValue cur = arr.get(mid);
            if(cur.timeStamp <= target){
                matchIndex = mid;
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }

        if(matchIndex == -1){
            return Optional.empty();
        }

        return Optional.of(arr.get(matchIndex));
    }
}
