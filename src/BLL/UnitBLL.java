package BLL;

import DAL.Unit;
import Getway.UnitGetway;

/**
 * Created by rifat on 8/15/15.
 */
public class UnitBLL {
    
    UnitGetway unitGetway = new UnitGetway();
    
    public Object save(Unit unit){
        if(unitGetway.isUniqName(unit)){
            unitGetway.save(unit);
        }
        return unit;
    }

    public Object delete(Unit unit){
        if(unitGetway.isNotUse(unit)){
            unitGetway.delete(unit);
        }
        return unit;
    }
}
