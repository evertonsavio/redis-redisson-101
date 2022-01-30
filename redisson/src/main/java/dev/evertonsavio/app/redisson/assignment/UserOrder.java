package dev.evertonsavio.app.redisson.assignment;

public class UserOrder {
    private int id;
    private Category category;

    public UserOrder() {
    }

    public UserOrder(int id, Category category) {
        this.id = id;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "UserOrder{" +
                "id=" + id +
                ", category=" + category +
                '}';
    }
}
