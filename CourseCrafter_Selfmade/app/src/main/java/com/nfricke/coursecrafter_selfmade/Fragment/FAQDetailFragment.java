package com.nfricke.coursecrafter_selfmade.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.nfricke.coursecrafter_selfmade.Listener_Handler.FAQDetailAssetSchemeHandler;
import com.nfricke.coursecrafter_selfmade.DAO.FAQ;
import com.nfricke.coursecrafter_selfmade.R;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import io.noties.markwon.Markwon;
import io.noties.markwon.image.ImagesPlugin;


public class FAQDetailFragment extends Fragment {

    //Initialize detail FAQ fragment to display Markdown file
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_faq_detail, container, false);

        FAQ faq = null;
        Bundle bundle = getArguments();
        if (bundle != null) {
            faq = (FAQ) bundle.getSerializable("faq");
        }

        //Initialize layout links
        TextView labelView = view.findViewById(R.id.detail_label);
        TextView titleView = view.findViewById(R.id.detail_title);
        TextView subtitleView = view.findViewById(R.id.detail_subtitle);
        TextView markdownTextView = view.findViewById(R.id.markdown_text_view);

        labelView.setText(faq.getLabel());
        titleView.setText(faq.getTitle());
        subtitleView.setText(faq.getSubtitle());

        // Read and display Markdown from file
        String markdownText = readMarkdownFromAssets(requireContext(), faq.getContent1());

        Markwon markwon = Markwon.builder(requireContext())
                .usePlugin(ImagesPlugin.create(new ImagesPlugin.ImagesConfigure() {
                    @Override
                    public void configureImages(ImagesPlugin plugin) {
                        plugin.addSchemeHandler(new FAQDetailAssetSchemeHandler(requireContext()));
                    }
                }))
                .build();

        markwon.setMarkdown(markdownTextView, markdownText != null ? markdownText : "");

        return view;
    }

    // Function for reading the Markdown file
    private String readMarkdownFromAssets(Context context, String fileName) {
        try {
            InputStream inputStream = context.getAssets().open(fileName);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            return new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}

