package com.example.renan.calculator;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class InputFragment extends Fragment {

    private CalculatorContract.ForwardInputInteractionPresenter forwardInteraction;

    public void setPresenter(CalculatorContract.ForwardInputInteractionPresenter forwardInteraction){
        this.forwardInteraction = forwardInteraction;
    }

    public InputFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_input, container, false);

        ButterKnife.bind(this, v);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick({
            R.id.btn_number_one, R.id.btn_number_two, R.id.btn_number_three, R.id.btn_number_four,
            R.id.btn_number_five, R.id.btn_number_six, R.id.btn_number_seven, R.id.btn_number_eight,
            R.id.btn_number_nine, R.id.btn_number_zero
    })
    public void onNumberClick(Button b){
        forwardInteraction.onNumberClick(Integer.parseInt(b.getText().toString()));
    }

    @OnClick({
            R.id.btn_operator_add, R.id.btn_operator_divide,
            R.id.btn_operator_multiply, R.id.btn_operator_subtract
    })
    public void onOperatorClick(Button b){
        forwardInteraction.onOperatorClick(b.getText().toString());
    }

    @OnClick(R.id.btn_decimal)
    public void onDecimalClick(Button b){
        forwardInteraction.onDecimalClick();
    }

    @OnClick(R.id.btn_evalute)
    public void onEvaluateClick(Button b){
        forwardInteraction.onEvaluateClick();
    }

    public static InputFragment newInstance(){
        return new InputFragment();
    }
}
