package www.sydlinaonline.com.sydlinaonline.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import www.sydlinaonline.com.sydlinaonline.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChoosePharmacyFragment extends Fragment {


    public ChoosePharmacyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_pharmacy, container, false);
    }

}
