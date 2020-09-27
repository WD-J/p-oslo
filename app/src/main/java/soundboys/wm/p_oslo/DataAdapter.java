package soundboys.wm.p_oslo;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> implements Filterable {

    ArrayList<String> mGatenavnList;
    private ArrayList<String> mStrekningList;
    private ArrayList<String> mDatoList;
    private ArrayList<String> mTidspunktList;
    private static ClickListener clickListener;

    private Activity mActivity;
    private int lastPosition = -1;
    CustomFilter filter;

    public DataAdapter(Class1 activity, ArrayList<String> mGatenavnList, ArrayList<String> mStrekningList, ArrayList<String> mDatoList, ArrayList<String> mTidspunktList) {
        this.mActivity = activity;
        this.mGatenavnList = mGatenavnList;
        this.mStrekningList = mStrekningList;
        this.mDatoList = mDatoList;
        this.mTidspunktList = mTidspunktList;
    }

    public DataAdapter(Class2 activity, ArrayList<String> mGatenavnList, ArrayList<String> mStrekningList, ArrayList<String> mDatoList, ArrayList<String> mTidspunktList) {
        this.mActivity = activity;
        this.mGatenavnList = mGatenavnList;
        this.mStrekningList = mStrekningList;
        this.mDatoList = mDatoList;
        this.mTidspunktList = mTidspunktList;
    }

    public DataAdapter(Class3 activity, ArrayList<String> mGatenavnList, ArrayList<String> mStrekningList, ArrayList<String> mDatoList, ArrayList<String> mTidspunktList) {
        this.mActivity = activity;
        this.mGatenavnList = mGatenavnList;
        this.mStrekningList = mStrekningList;
        this.mDatoList = mDatoList;
        this.mTidspunktList = mTidspunktList;
    }

    public DataAdapter(Class4 activity, ArrayList<String> mGatenavnList, ArrayList<String> mStrekningList, ArrayList<String> mDatoList, ArrayList<String> mTidspunktList) {
        this.mActivity = activity;
        this.mGatenavnList = mGatenavnList;
        this.mStrekningList = mStrekningList;
        this.mDatoList = mDatoList;
        this.mTidspunktList = mTidspunktList;
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new CustomFilter(mGatenavnList, this);
        }

        return filter;
    }

    public interface ClickListener {
        void onItemClick(String title, View v);
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        DataAdapter.clickListener = clickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {

        private TextView gatenavn, strekning, dato, tidspunkt;

        public MyViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            gatenavn = view.findViewById(R.id.gatenavn);
            strekning = view.findViewById(R.id.strekning);
            dato = view.findViewById(R.id.dato);
            tidspunkt = view.findViewById(R.id.tidspunkt);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(mGatenavnList.get(getAdapterPosition()), v);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_data, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.gatenavn.setText(mGatenavnList.get(position));
        holder.strekning.setText(mStrekningList.get(position));
        holder.dato.setText(mDatoList.get(position));
        holder.tidspunkt.setText(mTidspunktList.get(position));
    }

    @Override
    public int getItemCount() {
        return mGatenavnList.size();
    }

    void reloadData(ArrayList<String> mGatenavnList, ArrayList<String> mStrekningList, ArrayList<String> mDatoList, ArrayList<String> mTidspunktList) {
        this.mGatenavnList = mGatenavnList;
        this.mStrekningList = mStrekningList;
        this.mDatoList = mDatoList;
        this.mTidspunktList = mTidspunktList;
        notifyDataSetChanged();
    }

    void reloadDataByFilter(ArrayList<String> mGatenavnList) {
        this.mGatenavnList = mGatenavnList;
        notifyDataSetChanged();
    }
}