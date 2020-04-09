import java.util.concurrent.*;

public class WorkerCallable implements Callable<Float> {
    private static final ConcurrentHashMap<String, Float> map;
    private String partName;

    static {
        map = new ConcurrentHashMap<>();
        map.put("Axle", 238.57f);
        map.put("Gear", 45.57f);
        map.put("Wheel", 23.57f);
        map.put("Rotor", 56.57f);
    }

    public WorkerCallable(String partName){
        this.partName = partName;
    }

    @Override
    public Float call() throws Exception {
        float price = map.get(this.partName);
        System.out.pritnln("WorkerCallable returned" +price);
        return price;
    }
}