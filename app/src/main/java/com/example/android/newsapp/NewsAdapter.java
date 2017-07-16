package com.example.android.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormatSymbols;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * {@link NewsAdapter} is an {@link ArrayAdapter} that can provide the layout for each list
 * based on a data source, which is a list of {@link Result} objects.
 */
public class NewsAdapter extends ArrayAdapter<Result> {
    /**
     * @param context The current context. Used to inflate the layout file.
     * @param news    A List of Result objects to display in a list
     */

    public NewsAdapter(Context context, List<Result> news) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, news);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        NewsAdapter.ViewHolder holder;

        if (listItemView != null) {

            holder = (ViewHolder) listItemView.getTag();
        } else {

            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list_item, parent, false);
            holder = new ViewHolder(listItemView);
            listItemView.setTag(holder);
        }

        // Get the {@link Result} object located at this position in the list
        Result currentArticle = getItem(position);

        // Find the TextView with view ID publication_date
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(currentArticle.getPublicationDate());
        // Display the date of the current result in the TextView
        holder.publicationDateTextView.setText(formattedDate);
        holder.publicationDateTextView.setVisibility(View.VISIBLE);

        // Find the TextView with view ID section_name
        if (currentArticle.getSectionName().isEmpty())
            holder.sectionNameTextView.setVisibility(View.GONE);
        else {
            holder.sectionNameTextView.setText(currentArticle.getSectionName());
            holder.sectionNameTextView.setVisibility(View.VISIBLE);
        }

        // Find the TextView with view ID title and hide it, if it is empty
        if (currentArticle.getTitle().isEmpty()) holder.titleTextView.setVisibility(View.GONE);
        else {
            holder.titleTextView.setText(currentArticle.getTitle());
            holder.titleTextView.setVisibility(View.VISIBLE);
        }

        // Find the TextView with view ID author. It can't be hided as next to this view is
        // publication date view and we don't want to be in the same line with title
        if (currentArticle.getAuthors().isEmpty()) holder.authorsTextView.setVisibility(View.GONE);
        else {
            holder.authorsTextView.setText(currentArticle.getAuthors());
            holder.authorsTextView.setVisibility(View.VISIBLE);
        }

        return listItemView;
    }

    /**
     * Return the formatted date string dd/mm/yyyy from a String yyyy-mm-dd.
     */
    private String formatDate(String stringDate) {
        String year = stringDate.substring(0, 4);
        String month = stringDate.substring(5, 7);
        month = new DateFormatSymbols().getMonths()[Integer.parseInt(month) - 1];
        month = month.substring(0, 3);
        String day = stringDate.substring(8, 10);
        String newString = month + " " + day + ", " + year;
        return newString;
    }

    static class ViewHolder {
        @BindView(R.id.publication_date)
        TextView publicationDateTextView;
        @BindView(R.id.section_name)
        TextView sectionNameTextView;
        @BindView(R.id.title)
        TextView titleTextView;
        @BindView(R.id.authors)
        TextView authorsTextView;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}


