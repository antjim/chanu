package com.chanapps.four.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.BaseAdapter;
import com.chanapps.four.activity.R;
import com.chanapps.four.data.ChanBoard;
import com.chanapps.four.data.ChanWatchlist;
import com.chanapps.four.data.ToastRunnable;

/**
* Created with IntelliJ IDEA.
* User: arley
* Date: 12/14/12
* Time: 12:44 PM
* To change this template use File | Settings | File Templates.
*/
public class WatchlistClearDialogFragment extends DialogFragment {
    public static final String TAG = WatchlistClearDialogFragment.class.getSimpleName();
    private BoardGroupFragment fragment;
    public WatchlistClearDialogFragment(BoardGroupFragment fragment) {
        super();
        this.fragment = fragment;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return (new AlertDialog.Builder(getActivity()))
                .setMessage(R.string.dialog_clear_watchlist)
                .setPositiveButton(R.string.dialog_clear,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Context ctx = getActivity();
                                ChanWatchlist.clearWatchlist(ctx);
                                BaseAdapter adapter = fragment.getAdapter();
                                if (adapter != null)
                                    adapter.notifyDataSetChanged();
                                (new ToastRunnable(getActivity(), getString(R.string.dialog_cleared_watchlist))).run();
                            }
                        })
                .setNegativeButton(R.string.dialog_cancel,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // ignore
                            }
                        })
                .create();
    }
}
