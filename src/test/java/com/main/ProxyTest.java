package com.main;

import com.classes.TargetClass;
import com.interfaces.TargetInterface;
import com.proxy.ProxyHandler;
import org.junit.Test;

import java.lang.reflect.*;

/**
 * Created by 淋漓尽致 on 2016/12/7.
 */

public class ProxyTest {

    /**
     * 该方法中，首先会创建被代理类的对象。代理处理器ProxyHandler中会以java反射的方式调用被代理类中的方法。
     * 然后使用java反射机制的Proxy.newProxyInstance创建一个实现业务接口的代理类用于访问业务，需要指定被代理类的类加载器，需要实现的接口，处理被代理类的处理器
     * 调用被代理类的目标接口方法时，会先转发到代理处理器中的invokde方法，invoke方法内部实现预处理，在调用被代理类的方法
     */
    @Test
    public void mainTest() {
        TargetClass targetClass = new TargetClass();//被代理的对象
        InvocationHandler proxyHandler =  new ProxyHandler(targetClass);//代理类处理被代理对象

        //创建一个实现业务接口的代理类，用于访问业务类
        //返回一个指定接口的代理类，该接口可以将方法调用指派到指定的调用处理程序，如proxyhandler
        TargetInterface targetInterface = (TargetInterface)Proxy.newProxyInstance(targetClass.getClass().getClassLoader(),
                targetClass.getClass().getInterfaces(), proxyHandler);
        System.out.println("classLoader------"+targetClass.getClass().getClassLoader().getClass().getName());
        System.out.println("interface------"+targetClass.getClass().getInterfaces()[0].getName());
        System.out.println("targetInterface-----"+targetInterface.getClass().getName());
        targetInterface.targetMethodA(5);
        targetInterface.targetMethodB(10);
        targetInterface.methodC(4);

        printClassDefinition(targetClass.getClass());
    }

    public static String getModifier(int modifier){
        String result = "";
        switch(modifier){
            case Modifier.PRIVATE:
                result = "private";break;
            case Modifier.PUBLIC:
                result = "public";break;
            case Modifier.PROTECTED:
                result = "protected";break;
            case Modifier.ABSTRACT :
                result = "abstract";break;
            case Modifier.FINAL :
                result = "final";break;
            case Modifier.NATIVE :
                result = "native";break;
            case Modifier.STATIC :
                result = "static";break;
            case Modifier.SYNCHRONIZED :
                result = "synchronized";break;
            case Modifier.STRICT  :
                result = "strict";break;
            case Modifier.TRANSIENT :
                result = "transient";break;
            case Modifier.VOLATILE :
                result = "volatile";break;
            case Modifier.INTERFACE :
                result = "interface";break;
        }
        return result;
    }

    public static void printClassDefinition(Class clz){

        String clzModifier  = getModifier(clz.getModifiers());
        if(clzModifier!=null && !clzModifier.equals("")){
            clzModifier = clzModifier + " ";
        }

        String superClz = clz.getSuperclass().getName();
        if(superClz!=null && !superClz.equals("")){
            superClz = "extends " + superClz;
        }

        Class[] interfaces = clz.getInterfaces();

        String inters = "";
        for(int i=0; i<interfaces.length; i++){
            if(i==0){
                inters += "implements ";
            }
            inters += interfaces[i].getName();
        }
        //targetClass的父类和实现的接口
        System.out.println(clzModifier +clz.getName()+" " + superClz +" " + inters );
        System.out.println("{");


        //得到类中声明的变量
        Field[] fields = clz.getDeclaredFields();
        for(int i=0; i<fields.length; i++){
            String modifier = getModifier(fields[i].getModifiers());
            if(modifier!=null && !modifier.equals("")){
                modifier = modifier + " ";
            }
            String fieldName = fields[i].getName();
            String fieldType = fields[i].getType().getName();
            System.out.println("    "+modifier + fieldType + " "+ fieldName + ";");
        }

        System.out.println();

        //得到类中声明的方法
        Method[] methods = clz.getDeclaredMethods();
        for(int i=0; i<methods.length; i++){
            Method method = methods[i];

            String modifier = getModifier(method.getModifiers());
            if(modifier!=null && !modifier.equals("")){
                modifier = modifier + " ";
            }

            String methodName = method.getName();

            Class returnClz = method.getReturnType();
            String retrunType = returnClz.getName();

            Class[] clzs = method.getParameterTypes();
            String paraList = "(";
            for(int j=0; j<clzs.length; j++){
                paraList += clzs[j].getName();
                if(j != clzs.length -1 ){
                    paraList += ", ";
                }
            }
            paraList += ")";

            clzs = method.getExceptionTypes();
            String exceptions = "";
            for(int j=0; j<clzs.length; j++){
                if(j==0){
                    exceptions += "throws ";
                }

                exceptions += clzs[j].getName();

                if(j != clzs.length -1 ){
                    exceptions += ", ";
                }
            }

            exceptions += ";";

            String methodPrototype = modifier +retrunType+" "+methodName+paraList+exceptions;

            System.out.println("    "+methodPrototype );

        }
        System.out.println("}");
    }



    //再改写main方法



}
