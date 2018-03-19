package javaWorld;

import kotlinInAction.StringFunctions;

import java.util.Collections;

import static kotlinInAction.StringFunctions.joinToString;

public class Test {
    public static void main(String[] args) {
        StringFunctions.main(new String[]{"1"});
        System.out.println(joinToString(Collections.singletonList(1)));
    }

    private void privateFun() {

    }

    protected void protectedFun() {

    }

}

class SubTest extends Test {

    @Override
    protected void protectedFun() {
        super.protectedFun();
    }
}