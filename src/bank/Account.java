package bank;

import java.util.Objects;

public class Account{
    private long ammount;
    private boolean isLocked;

    public Account(long ammount, boolean isLocked) {
        this.ammount = ammount;
        this.isLocked = isLocked;
    }

    public long getAmmount() {
        return ammount;
    }

    public void setAmmount(long ammount) {
        this.ammount = ammount;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Account account = (Account) o;
        return ammount == account.ammount &&
                isLocked == account.isLocked;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ammount, isLocked);
    }

    @Override
    public String toString() {
        return "ammount=" + ammount +
                ", isLocked=" + isLocked;
    }
}
