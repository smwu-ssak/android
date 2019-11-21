package com.example.seed;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

// Customized by MS

public class DetailProductDialog extends Dialog implements OnClickListener {
    Button buttonOK;
    Button buttonCancel;

    public DetailProductDialog(Context context) {
        super(context);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_detail_products);

        buttonOK = (Button)findViewById(R.id.dialog_basket_yes_btn);
        buttonCancel = (Button)findViewById(R.id.dialog_basket_later_btn);

        buttonOK.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == buttonOK) {
            moveToBasket();
        }
        if (view == buttonCancel) {
            moveToMain();
        }
        dismiss();
    }

    public void moveToBasket() {
        Intent intent = new Intent(getContext(), BasketActivity.class);
        getContext().startActivity(intent);
    }

    public void moveToMain() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        getContext().startActivity(intent);
    }
}