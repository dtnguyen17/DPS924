package dtnguyen17.myfriends;

/**
 * Created by DanielNguyen on 2015-10-29.
 */
public class Friends {

    private int _id;
    private String _name;
    private String _phone;
    private String _email;

    public Friends() {}

    public void setID(int id) { this._id = id;}

    public int getID() { return this._id;}

    public void setName(String name) {
        this._name = name;
    }

    public String getName() {
        return this._name;
    }

    public void setPhone(String phone) {
        this._phone = phone;
    }

    public String getPhone() {
        return this._phone;
    }

    public void setEmail(String email) {
        this._email = email;
    }

    public String getEmail() {
        return this._email;
    }
}
