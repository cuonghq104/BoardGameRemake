package techkids.cuong.myapplication.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

import java.io.IOException;

import techkids.cuong.myapplication.R;
import techkids.cuong.myapplication.adapters.MyRuleSearchResultRecyclerViewAdapter;
import techkids.cuong.myapplication.fragments.dummy.DummyContent;
import techkids.cuong.myapplication.fragments.dummy.DummyContent.DummyItem;

import static techkids.cuong.myapplication.activities.RuleActivity.PDF_FILE_NAME_KEY;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class RuleSearchResultFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String TAG = RuleSearchResultFragment.class.toString();
    // TODO: Customize parameters


    private int mColumnCount = 1;
    private String fileName;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RuleSearchResultFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static RuleSearchResultFragment create(String fileName) {
        RuleSearchResultFragment fragment = new RuleSearchResultFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, 1);
        args.putString(PDF_FILE_NAME_KEY,fileName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            fileName = getArguments().getString(PDF_FILE_NAME_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rule_search_result_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyRuleSearchResultRecyclerViewAdapter(DummyContent.ITEMS, mListener));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnListFragmentInteractionListener");
            mListener = new OnListFragmentInteractionListener() {
                @Override
                public void onListFragmentInteraction(DummyItem item) {
                    //do nothign
                }
            };
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: start reading");
        try {
            readFromInternal(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "onStart: finish reading");
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume: ");
        super.onResume();
    }

    public void readFromInternal(String fileName) throws IOException {


        //readFromINternal
        String filePath = getActivity().getFilesDir() + "/" + fileName;
        PdfReader pdfReader = new PdfReader(filePath);

        for (int page = 1; page <= pdfReader.getNumberOfPages(); page++) {
            TextExtractionStrategy strategy = new SimpleTextExtractionStrategy();
            String currentText = PdfTextExtractor.getTextFromPage(pdfReader, page,
                    strategy);
            Log.d(TAG, String.format("--readFromInternal: page %s:", page));
            Log.d(TAG, currentText);
//            currentText = Xml.Encoding.UTF_8.GetString(ASCIIEncoding.Convert(Encoding.Default, Encoding.UTF8, Encoding.Default.GetBytes(currentText)));

        }
        pdfReader.close();

    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }
}
