package DO;

import DO.implementation.employeeDO;
import DO.implementation.itemDO;
import DO.implementation.transactionlogDO;

public class FactoryDO {

    private static FactoryDO factoryDO;

    public FactoryDO() {
    }

    public static FactoryDO getInstance(){
        if (factoryDO == null){
            factoryDO = new FactoryDO();
        }
        return factoryDO;
    }

    public enum DOtypes{
        EMPLOYEE, ITEM, TRANSACTION_LOG
    }

    public Object getDO(DOtypes types){
        switch(types){
            case EMPLOYEE:
                return new employeeDO();
            case ITEM:
                return new itemDO();
            case TRANSACTION_LOG:
                return new transactionlogDO();
            default:
                return null;
        }
    }
}

