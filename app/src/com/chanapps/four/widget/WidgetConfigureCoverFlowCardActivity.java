package com.chanapps.four.widget;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.chanapps.four.activity.R;
import com.chanapps.four.component.AnalyticsComponent;
import com.chanapps.four.data.ChanBoard;

/**
 * Created with IntelliJ IDEA.
 * User: johnarleyburns
 * Date: 1/15/13
 * Time: 11:07 PM
 */
public class WidgetConfigureCoverFlowCardActivity extends WidgetConfigureCoverFlowActivity {

    public static final String TAG = WidgetConfigureCoverFlowCardActivity.class.getSimpleName();
    private static final boolean DEBUG = false;

    @Override
    protected int getContentViewLayout() {
        return R.layout.widget_configure_coverflowcard_layout;
    }

    @Override
    protected Class getWidgetProviderClass() {
        return BoardCoverFlowCardWidgetProvider.class;
    }

    @Override
    protected String getWidgetType() {
        return WidgetConstants.WIDGET_TYPE_COVER_FLOW_CARD;
    }

    @Override
    protected BaseAdapter createAdapter() {
        return new CardCoverflowStackAdapter(this, R.layout.widget_coverflowcard_item, R.id.widget_coverflowcard_image);
    }

    protected class CardCoverflowStackAdapter extends CoverflowStackAdapter {

        public CardCoverflowStackAdapter(Context context, int layoutId, int imageId) {
            super(context, layoutId, imageId);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            View item = super.getView(position, view, parent);
            ChanBoard board = ChanBoard.getBoardByCode(context, widgetConf.boardCode);
            TextView sub = (TextView)item.findViewById(R.id.widget_coverflowcard_subject);
            String html = "<b>" + board.name + "</b><br/>" + board.getDescription(context);
            sub.setText(Html.fromHtml(html));
            return item;
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        AnalyticsComponent.onStart(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        AnalyticsComponent.onStop(this);
    }

}