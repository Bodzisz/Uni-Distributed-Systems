public class ServerItem {

    private String pesel;
    private String name;
    private int age;

    private String fileName;

    public ServerItem(final String pesel, final String name, final int age, final String fileName) {
        this.pesel = pesel;
        this.name = name;
        this.age = age;
        this.fileName = fileName;
    }

    public String getPesel() {
        return pesel;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public int hashCode() {
        return pesel.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        ServerItem serverItem = (ServerItem) o;
        return this.pesel.equals(serverItem.getPesel());
    }
}
