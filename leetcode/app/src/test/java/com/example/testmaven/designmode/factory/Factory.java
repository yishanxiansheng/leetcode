package com.example.testmaven.designmode.factory;

/**
 * @author heshufan
 * @date 2021-01-10
 */
public interface Factory {

    void process();

    static Factory getFactory(String s){
        if (s.equals("Circle")){
            return new CircleFactory(s);
        }else if (s.equals("Rectangle")){
            return new RectangleFactory(s);
        }
        return null;
    }
}
