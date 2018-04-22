package com.example.renan.calculator;

import org.javia.arity.Symbols;
import org.javia.arity.SyntaxException;

/**
 * Created by renan on 22/04/2018.
 */

public class Calculation {

    private final Symbols symbols;

    private CalculationResult calculationResult;
    private static String currentExpression;

    interface CalculationResult{
        void onExpressionChanged(String result, boolean success);
    }

    public Calculation(){
        symbols = new Symbols();
    }

    public void setCalculationResultListener(CalculationResult calculationResult){
        this.calculationResult = calculationResult;
        currentExpression = "";
    }

    public void deleteCharacter(){
        if(currentExpression.length() > 0){
            currentExpression = currentExpression.substring(0, currentExpression.length() - 1);
            calculationResult.onExpressionChanged(currentExpression, true);
        }else{
            calculationResult.onExpressionChanged("Invalid input.", false);
        }
    }

    public void deleteExpression(){
        if(currentExpression.equals("")) {
            calculationResult.onExpressionChanged("Invalid input.", false);
        }
        currentExpression = "";
        calculationResult.onExpressionChanged(currentExpression, true);
    }

    public void appendNumber(String number){
        if(currentExpression.startsWith("0") && number.equals("0")){
            calculationResult.onExpressionChanged("Invalid input.", false);
        }else{
            if(currentExpression.length() <= 16){
                currentExpression += number;
                calculationResult.onExpressionChanged(currentExpression, true);
            }else{
                calculationResult.onExpressionChanged("Expression too long", false);
            }
        }
    }

    public void appendOperator(String operator){
        if(validateExpression(currentExpression)){
            currentExpression += operator;
            calculationResult.onExpressionChanged(currentExpression, true);
        }
    }

    public void appendDecimal(){
        if(validateExpression(currentExpression)){
            currentExpression += ",";
            calculationResult.onExpressionChanged(currentExpression, true);
        }
    }

    public void performEvaluate(){
        if(validateExpression(currentExpression)){
            try{
                Double result = symbols.eval(currentExpression);
                currentExpression = Double.toString(result);
                calculationResult.onExpressionChanged(currentExpression, true);
            }catch (SyntaxException e) {
                calculationResult.onExpressionChanged("Invalid input.", false);
                e.printStackTrace();
            }
        }
    }

    public boolean validateExpression(String expression){
        if(expression.endsWith("*") || expression.endsWith("/")
                || expression.endsWith("-") || expression.endsWith("+")){
            calculationResult.onExpressionChanged("Invalid input.", false);
            return false;
        }else if(expression.equals("")){
            calculationResult.onExpressionChanged("Empty expression", false);
            return false;
        }else if(expression.length() > 16){
            calculationResult.onExpressionChanged("Expression too long", false);
            return false;
        }else{
            return true;
        }
    }
}
