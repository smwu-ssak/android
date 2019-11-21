package com.example.seed;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

// Customized by MS

public class BuyProductDialog extends Dialog implements OnClickListener {
    Button button;
    Context context;

    public BuyProductDialog(Context context) {
        super(context);
        context = context;

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_buy_products);

        button = (Button)findViewById(R.id.dialog_buy_products_confirm_btn);

        button.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view == button) {
            Intent intent = new Intent(getContext(), MainActivity.class);
            // Mainactivity로 화면 전환하면서 백 스택 지우기
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            getContext().startActivity(intent);
        }
        dismiss();
    }
}