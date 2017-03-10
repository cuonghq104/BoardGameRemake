package techkids.cuong.myapplication.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.shockwave.pdfium.PdfDocument;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import techkids.cuong.myapplication.R;
import techkids.cuong.myapplication.activities.RuleActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class RulePDFViewingFragment extends Fragment implements OnPageChangeListener, OnLoadCompleteListener {


    private static final String TAG = RulePDFViewingFragment.class.toString();
    @BindView(R.id.pdfView)
    PDFView pdfView;

    String pdfFileName;


    int pageNumber = 0;
    private boolean isLoaded = false;

    public RulePDFViewingFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        Log.d(TAG, String.format("onCreateView: page number = %s", pageNumber));
        return inflater.inflate(R.layout.fragment_rule_pdfviewing, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
//        EventBus.getDefault().register(this);
        Log.d(TAG, "onStart: ");
        isLoaded = false;
        displayFromAsset(pdfFileName);

    }

    @Override
    public void onStop() {
        super.onStop();
//        EventBus.getDefault().unregister(this);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated: ");
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        getArgs();
        getActivity().setTitle(pdfFileName);
        isLoaded = false;
    }


    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.d(TAG, "onCreateOptionsMenu: ");
        if (isLoaded) {
            inflater.inflate(R.menu.rules_nosearch_menu,menu);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_bookmarks:
                EventBus.getDefault().post(new RuleActivity.ChangeToMenuContentFragmentEvent());
                Log.d(TAG, "onOptionsItemSelected: R.id.action_bookmarks");
                return true;
            case android.R.id.home:
                getActivity().onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void displayFromAsset(String assetFileName) {

        Log.d(TAG, String.format("displayFromAsset: page number = %s", pageNumber));
        pdfView.fromAsset(assetFileName)
                .defaultPage(pageNumber)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(new DefaultScrollHandle(getContext()))
                .load();

    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
//        setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
    }

    public void putPageNumber(int pageNumber) {
        getArguments().putInt(START_PAGE_NUMBER_KEY,pageNumber);
    }

    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = pdfView.getDocumentMeta();
        if (meta == null) {
            Toast.makeText(getContext(), "meta == null", Toast.LENGTH_SHORT).show();
        } else {
            Log.e(TAG, "title = " + meta.getTitle());
            Log.e(TAG, "author = " + meta.getAuthor());
            Log.e(TAG, "subject = " + meta.getSubject());
            Log.e(TAG, "keywords = " + meta.getKeywords());
            Log.e(TAG, "creator = " + meta.getCreator());
            Log.e(TAG, "producer = " + meta.getProducer());
            Log.e(TAG, "creationDate = " + meta.getCreationDate());
            Log.e(TAG, "modDate = " + meta.getModDate());
        }
//        printBookmarksTree(pdfView.getTableOfContents(), "-");
        EventBus.getDefault().post(new OnPDFLoadedEvent(pdfView.getTableOfContents()));
        isLoaded = true;
        getActivity().invalidateOptionsMenu();

    }

    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
        Log.d(TAG, String.format("printBookmarksTree: %s", tree.size()));
        for (PdfDocument.Bookmark b : tree) {

            Log.e(TAG, String.format("%s %s, p %d", sep, b.getTitle(), b.getPageIdx()));
            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + "-");
            }
        }
    }

    private void setPdfFileName(String pdfFileName) {
        this.pdfFileName = pdfFileName;
    }

    public static RulePDFViewingFragment create(String pdfFileName,int startPageNumber){
        RulePDFViewingFragment fragment = new RulePDFViewingFragment();
        Bundle args = new Bundle();
        args.putString(PDF_FILE_NAME_KEY,pdfFileName);
        args.putInt(START_PAGE_NUMBER_KEY, startPageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    private static  String PDF_FILE_NAME_KEY = "pdfFileName";
    private static final String START_PAGE_NUMBER_KEY = "start page number";

    public void getArgs() {
        Bundle args = getArguments();
        pdfFileName = args.getString(PDF_FILE_NAME_KEY);
        pageNumber =  args.getInt(START_PAGE_NUMBER_KEY);
    }

    public static class OnPDFLoadedEvent{
        private List<PdfDocument.Bookmark> bookmarks;

        public OnPDFLoadedEvent(List<PdfDocument.Bookmark> bookmarks) {
            this.bookmarks = bookmarks;
        }

        public List<PdfDocument.Bookmark> getBookmarks() {
            return bookmarks;
        }
    }
}
