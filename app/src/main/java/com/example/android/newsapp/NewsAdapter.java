package com.example.android.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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
     * @param news    A List of Book objects to display in a list
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

        // Get the {@link Book} object located at this position in the list
        Result currentArticle = getItem(position);


        // Find the TextView with view ID subtitle and hide it, if it is empty
          /*  if (currentArticle.getType() == null) holder.typeTextView.setVisibility(View.GONE);
            else {
                holder.typeTextView.setText(currentArticle.getType());
                holder.typeTextView.setVisibility(View.VISIBLE);
            }
*/
        // Find the TextView with view ID authors and hide it, if it is empty
        if (currentArticle.getSectionName().isEmpty())
            holder.sectionNameTextView.setVisibility(View.GONE);
        else {
            holder.sectionNameTextView.setText(currentArticle.getSectionName());
            holder.sectionNameTextView.setVisibility(View.VISIBLE);
        }

        // Find the TextView with view ID authors and hide it, if it is empty
        // if (currentArticle.getPublicationDate()) holder.publicationDateTextView.setVisibility(View.GONE);
        // else {

        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(currentArticle.getPublicationDate());
        // Display the date of the current earthquake in that TextView
        holder.publicationDateTextView.setText(formattedDate);
        holder.publicationDateTextView.setVisibility(View.VISIBLE);
        // }


        // Find the TextView with view ID subtitle and hide it, if it is empty
        if (currentArticle.getTitle().isEmpty()) holder.titleTextView.setVisibility(View.GONE);
        else {
            holder.titleTextView.setText(currentArticle.getTitle());
            holder.titleTextView.setVisibility(View.VISIBLE);
        }
        return listItemView;
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(String stringDate) {


        return stringDate;
    }

    static class ViewHolder {

        // @BindView(R.id.type) TextView typeTextView;
        @BindView(R.id.section_name)
        TextView sectionNameTextView;
        @BindView(R.id.publication_date)
        TextView publicationDateTextView;
        @BindView(R.id.title)
        TextView titleTextView;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


}


