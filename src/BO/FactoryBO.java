package BO;

public class FactoryBO {

    private static FactoryBO boFactory;

    public FactoryBO() {

    }

    public static FactoryBO getInstance(){
        if(boFactory == null){
            boFactory = new FactoryBO();
        }
        return boFactory;
    }

    public enum TypesBO{
        EMPLOYEE, ITEM, TRANSACTION_LOG
    }

    //public object getDO()
}
