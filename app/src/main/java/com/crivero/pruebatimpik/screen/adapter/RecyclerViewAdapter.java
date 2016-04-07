package com.crivero.pruebatimpik.screen.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.crivero.pruebatimpik.R;
import com.crivero.pruebatimpik.model.Employee;
import com.crivero.pruebatimpik.screen.MainActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by Christopher on 05/04/2016.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Employee> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        public TextView tvTitulo;
        public TextView tvSubTitulo;
        public ImageView imgContacto;

        public ViewHolder(View itemView, TextView tvTitulo, TextView tvSubTitulo, ImageView imgContacto) {
            super(itemView);
            this.tvTitulo = tvTitulo;
            this.tvSubTitulo = tvSubTitulo;
            this.imgContacto = imgContacto;
        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecyclerViewAdapter(List<Employee> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fila_lista, parent, false);
        // set the view's size, margins, paddings and layout parameters

        TextView tvTitulo = (TextView) v.findViewById(R.id.tv_titulo);
        TextView tvSubTitulo = (TextView) v.findViewById(R.id.tv_sub_titulo);
        ImageView imgContacto = (ImageView) v.findViewById(R.id.img_contacto);


        ViewHolder vh = new ViewHolder(v,tvTitulo,tvSubTitulo,imgContacto);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final MainActivity activity = new MainActivity();
        holder.tvTitulo.setText(mDataset.get(position).getName());
        final Bitmap[] bitmap = new Bitmap[1];
        //Thread para obtener las imagenes de la url, cualquier conexion a de realizarse en segundo plano.
        new Thread(new Runnable() {
            public void run() {
                bitmap[0] = LoadImageFromWebOperations(mDataset.get(position).getPic());
                //Le decimos que esta acción la realice en el mainThread, la acción es la de asignar
                // imagen al ImageView de la fila del RecyclerView
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        holder.imgContacto.setImageBitmap(bitmap[0]);
                    }
                });
            }
        }).start();

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    /**
     * Obtener imagen de URL
     * @param url se le pasa por parametro la url de la imagen
     * @return la imagen para ser asignada al ImageView
     */
    public static Bitmap LoadImageFromWebOperations(final String url) {
        InputStream is = null;
        Bitmap bitMap = null;

        URL connection = null;
        try {
            connection = new URL(url);
            is = connection.openConnection().getInputStream();
            bitMap = BitmapFactory.decodeStream(is);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitMap;
    }
}

