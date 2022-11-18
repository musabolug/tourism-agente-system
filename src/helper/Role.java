package helper;

public enum Role {
    MANAGER(1),
    OPERATOR(2);
    private int role;

    Role(int role){
        this.role = role;
    }
    public int getRole(){
        return this.role;
    }
    public void setRole(int role){
        this.role = role;
    }
    public static String getNameOfRole(int role){
        return switch (role){
            case 1 -> MANAGER.name();
            case 2 -> OPERATOR.name();
            default -> null;
        };
    }
}
