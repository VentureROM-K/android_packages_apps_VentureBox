package com.venturerom.venture;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.venturerom.venture.widget.Card;

public class ConfigCardViewCard extends Card{
	
	final Context mContext;
	LinearLayout llCardView;
	
	public ConfigCardViewCard(Context context, AttributeSet attrs, Bundle savedInstanceState) {
        super(context, attrs, savedInstanceState, false);
        
        mContext = context;

        setTitle(R.string.config_cardview);
        setLayoutId(R.layout.card_config_cardview);
        
        llCardView = (LinearLayout) findLayoutViewById(R.id.llCardView);

        Resources res = context.getResources();
        
        Switch switchEnable = (Switch) findLayoutViewById(R.id.cardview_enable);
        switchEnable.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            	Settings.System.putInt(getContext().getContentResolver(), "status_bar_recents_card_stack", isChecked ? 1 : 2);
            }
        });
        if(Settings.System.getString(getContext().getContentResolver(), "status_bar_recents_card_stack") != null){
        	if(Integer.valueOf(Settings.System.getString(getContext().getContentResolver(), "status_bar_recents_card_stack")) == 1){
            	switchEnable.setChecked(true);
            }
        }
        TextView tvHaloLabel = (TextView) findLayoutViewById(R.id.tvCardViewLabel);
        tvHaloLabel.setText(res.getString(R.string.cardviewlabel));
    }
	
	@Override
    public void expand() {
        super.expand();
        if (llCardView != null) {
        	llCardView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void collapse() {
        super.collapse();
        llCardView.setVisibility(View.GONE);
    }

    @Override
    public boolean canExpand() {
        return true;
    }

}
