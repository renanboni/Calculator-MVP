package com.example.renan.calculator;

/**
 * Created by renan on 22/04/2018.
 */

public interface CalculatorContract {

    interface PublishToView{
        void showResult(String result);

        void showToastMessage(String message);
    }

    interface ForwardDisplayInteractionPresenter{
        void onDeleteShortClick();

        void onDeleteLongClick();
    }

    interface ForwardInputInteractionPresenter{
        void onNumberClick(int number);

        void onDecimalClick();

        void onEvaluateClick();

        void onOperatorClick(String operator);
    }
}
