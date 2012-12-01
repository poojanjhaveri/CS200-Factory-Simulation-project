package factory.factory201.interfaces;

public interface Lane extends FactoryBase {

    public int capacity = 20;
    public int getIndex();
    //public boolean jammed=false;
    public void setJammed(boolean jammed);
    public boolean getJammed();
}