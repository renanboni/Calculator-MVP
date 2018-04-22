package com.example.renan.calculator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;


public class DisplayFragment extends Fragment implements CalculatorContract.PublishToView {

    @BindView(R.id.lbl_display)
    TextView mDisplay;

    private CalculatorContract.ForwardDisplayInteractionPresenter forwardInteraction;

    public void setPresenter(CalculatorContract.ForwardDisplayInteractionPresenter forwardInteraction){
        this.forwardInteraction = forwardInteraction;
    }

    public DisplayFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_display, container, false);

        ButterKnife.bind(this, v);

        return v;
    }

    public static DisplayFragment newInstance(){
        return new DisplayFragment();
    }

    @OnClick(R.id.imb_delete)
    public void onDeleteShortClick(View v){
        forwardInteraction.onDeleteShortClick();
    }

    @OnLongClick(R.id.imb_delete)
    public boolean onDeleteLongClick(View v){
        forwardInteraction.onDeleteLongClick();
        return true;
    }

    @Override
    public void showResult(String result) {
        mDisplay.setText(result);
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
