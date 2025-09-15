public class Customer {
    private int customerID;
    private String name;
    private String address;
    private String yardType;
    private double length;
    private double width;
    private double totalCost;

    // Default constructor
    public Customer() {
        customerID = 0;
        name = "Unknown";
        address = "Unknown";
        yardType = "grass";
        length = 0.0;
        width = 0.0;
        totalCost = 0.0;
    }

    // Parameterized constructor
    public Customer(int customerID, String name, String address, String yardType, double length, double width, double totalCost) {
        this.customerID = customerID;
        this.name = name;
        this.address = address;
        this.yardType = yardType;
        this.length = length;
        this.width = width;
        this.totalCost = totalCost;
    }

    public String toString() {
        return "Customer{ customerID=" + customerID + ", name=" + name + ", address=" + address +
                ", yardType=" + yardType + ", length=" + length + ", width=" + width + ", totalCost=" + totalCost + "}";
    }

    // Getters and Setters
    public int getCustomerID() { return customerID; }
    public void setCustomerID(int customerID) { this.customerID = customerID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getYardType() { return yardType; }
    public void setYardType(String yardType) { this.yardType = yardType; }

    public double getLength() { return length; }
    public void setLength(double length) { this.length = length; }

    public double getWidth() { return width; }
    public void setWidth(double width) { this.width = width; }

    public double getTotalCost() { return totalCost; }
    public void setTotalCost(double totalCost) { this.totalCost = totalCost; }
}
