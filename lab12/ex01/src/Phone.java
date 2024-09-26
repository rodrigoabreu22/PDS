public class Phone {
    private String processor;
    private double price;
    private int memory;
    private int camera;

    public Phone(String processor, double price, int memory, int camera) {
        this.processor = processor;
        this.price = price;
        this.memory = memory;
        this.camera = camera;
    }

    public String getProcessor() {
        return processor;
    }

    public double getPrice() {
        return price;
    }

    public int getMemory() {
        return memory;
    }

    public int getCamera() {
        return camera;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "processor='" + processor + '\'' +
                ", price=" + price +
                ", memory=" + memory +
                ", camera=" + camera +
                '}';
    }
}
