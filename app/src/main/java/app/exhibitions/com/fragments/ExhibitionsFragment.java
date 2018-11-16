package app.exhibitions.com.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import app.exhibitions.com.R;
import app.exhibitions.com.adapters.ExhibitionsAdapter;
import app.exhibitions.com.fileexhibitsloader.FileExhibitsLoader;
import app.exhibitions.com.model.Exhibit;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ExhibitionsFragment extends Fragment {

    private RecyclerView mExhibitionsRecycler;

    public ExhibitionsFragment() {

    }

    public static ExhibitionsFragment newInstance() {
        ExhibitionsFragment exhibitionsFramgent = new ExhibitionsFragment();
        return exhibitionsFramgent;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.exhibitions_fragment, null);

        mExhibitionsRecycler = view.findViewById(R.id.exhibitions_recycler);
        initRecycler();
        return view;
    }

    private MaybeObserver<List<Exhibit>> getExhibitsObserver(){
        return new MaybeObserver<List<Exhibit>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(List<Exhibit> exhibits) {
                ExhibitionsAdapter adapter = new ExhibitionsAdapter((ArrayList<Exhibit>)exhibits, getActivity());
                mExhibitionsRecycler.setAdapter(adapter);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    private MaybeOnSubscribe<List<Exhibit>> mMaybeOnsubscribe = new MaybeOnSubscribe<List<Exhibit>>() {
        public void subscribe(MaybeEmitter<List<Exhibit>> emitter) throws Exception {
           FileExhibitsLoader loader = new FileExhibitsLoader(getContext());
            if (!emitter.isDisposed()) {
                emitter.onSuccess(loader.getExhibitList());
            }
        }
    };

    private void initRecycler(){
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mExhibitionsRecycler.setLayoutManager(mLayoutManager);
        Maybe.create(mMaybeOnsubscribe)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getExhibitsObserver());
    }
}
