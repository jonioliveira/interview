package com.jonioliveira.interview.ui.feed.opensource;

import androidx.recyclerview.widget.LinearLayoutManager;
import dagger.Module;
import dagger.Provides;

@Module
public class OpenSourceFragmentModule {

    @Provides
    LinearLayoutManager provideLinearLayoutManager(OpenSourceFragment fragment) {
        return new LinearLayoutManager(fragment.getActivity());
    }

    @Provides
    OpenSourceAdapter provideOpenSourceAdapter() {
        return new OpenSourceAdapter();
    }

}
