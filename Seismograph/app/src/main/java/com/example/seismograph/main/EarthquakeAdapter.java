package com.example.seismograph.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seismograph.Earthquake;
import com.example.seismograph.R;
import com.example.seismograph.databinding.EarthquakeListItemBinding;

public class EarthquakeAdapter extends ListAdapter<Earthquake, EarthquakeAdapter.EarthquakeViewHolder> {

    Context context;

    public static final DiffUtil.ItemCallback<Earthquake> DIFF_CALLBACK = new DiffUtil.ItemCallback<Earthquake>() {
        @Override
        public boolean areItemsTheSame(@NonNull Earthquake oldItem, @NonNull Earthquake newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Earthquake oldItem, @NonNull Earthquake newItem) {
            return oldItem.equals(newItem);
        }
    };
    private OnItemClickListener onItemClickListener;

    protected EarthquakeAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    protected EarthquakeAdapter(@NonNull DiffUtil.ItemCallback<Earthquake> diffCallback) {
        super(diffCallback);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public EarthquakeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        EarthquakeListItemBinding binding = EarthquakeListItemBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new EarthquakeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EarthquakeViewHolder holder, int position) {
        Earthquake earthquake = getItem(position);
        holder.bind(earthquake);
    }

    interface OnItemClickListener {
        void onItemClick(Earthquake earthquake);
    }

    class EarthquakeViewHolder extends RecyclerView.ViewHolder {

        private final EarthquakeListItemBinding binding;

        public EarthquakeViewHolder(@NonNull EarthquakeListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Earthquake earthquake) {
            binding.magnitudeTextView.setText(context.getString(R.string.magnitude_format, earthquake.getMagnitude()));
            binding.placeTextView.setText(earthquake.getPlace());
            binding.getRoot().setOnClickListener(v -> onItemClickListener.onItemClick(earthquake));
            binding.executePendingBindings();
        }

    }
}