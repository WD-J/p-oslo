package soundboys.wm.p_oslo;

import android.widget.Filter;

import java.util.ArrayList;

public class CustomFilter extends Filter {

    DataAdapter adapter;
    private ArrayList<String> generalDataList;


    public CustomFilter(ArrayList<String> filteredGatenavnList,
                        DataAdapter adapter) {

        generalDataList = new ArrayList<>();
        generalDataList.addAll(filteredGatenavnList);
        this.adapter = adapter;
    }


    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();

        if (constraint != null && constraint.length() > 0) {
            constraint = constraint.toString().toUpperCase();
            ArrayList<String> filteredStrings = new ArrayList<>();

            for (int i = 0; i < generalDataList.size(); i++) {
                if (generalDataList.get(i).toUpperCase().contains(constraint)) {
                    filteredStrings.add(generalDataList.get(i));
                }
            }

            results.count = filteredStrings.size();
            results.values = filteredStrings;
        } else {
            results.count = generalDataList.size();
            results.values = generalDataList;

        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.reloadDataByFilter((ArrayList<String>) results.values);
    }
}