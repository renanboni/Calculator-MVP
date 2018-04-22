package com.example.renan.calculator;

/**
 * Created by renan on 22/04/2018.
 */

public class CalculatorPresenter implements CalculatorContract.ForwardDisplayInteractionPresenter,
    CalculatorContract.ForwardInputInteractionPresenter, Calculation.CalculationResult{

    private CalculatorContract.PublishToView publishResult;
    private Calculation calculation;

    public CalculatorPresenter(CalculatorContract.PublishToView publishResult){
        this.publishResult = publishResult;
        calculation = new Calculation();
        calculation.setCalculationResultListener(this);
    }

    @Override
    public void onDeleteShortClick() {
        calculation.deleteCharacter();
    }

    @Override
    public void onDeleteLongClick() {
        calculation.deleteExpression();
    }

    @Override
    public void onNumberClick(int number) {
        calculation.appendNumber(String.valueOf(number));
    }

    @Override
    public void onDecimalClick() {
        calculation.appendDecimal();
    }

    @Override
    public void onEvaluateClick() {
        calculation.performEvaluate();
    }

    @Override
    public void onOperatorClick(String operator) {
        calculation.appendOperator(operator);
    }

    @Override
    public void onExpressionChanged(String result, boolean success) {
        if(success){
            publishResult.showResult(result);
        }else{
            publishResult.showToastMessage(result);
        }
    }
}
