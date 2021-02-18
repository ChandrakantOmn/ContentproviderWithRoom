package com.cskapp.contentproviderwithroom


import android.annotation.SuppressLint
import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cskapp.contentproviderwithroom.data.AppModel
import com.cskapp.contentproviderwithroom.provider.SampleContentProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var mCheeseAdapter: CheeseAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        list.layoutManager = LinearLayoutManager(list.context)
        mCheeseAdapter = CheeseAdapter()
        list.adapter = mCheeseAdapter
        LoaderManager.getInstance(this).initLoader(LOADER_CHEESES, null, mLoaderCallbacks)
    }

    private val mLoaderCallbacks: LoaderManager.LoaderCallbacks<Cursor> = object : LoaderManager.LoaderCallbacks<Cursor> {
        override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
            return CursorLoader(applicationContext,
                SampleContentProvider.URI_APP_MODEL, arrayOf(AppModel.COLUMN_NAME),
                null, null, null)
        }

        override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor) {
            mCheeseAdapter!!.setCheeses(data)
        }

        override fun onLoaderReset(loader: Loader<Cursor>) {
            mCheeseAdapter!!.setCheeses(null)
        }
    }

    private class CheeseAdapter : RecyclerView.Adapter<CheeseAdapter.ViewHolder>() {
        private var mCursor: Cursor? = null
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(parent)
        }

        @SuppressLint("SetTextI18n")
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            if (mCursor!!.moveToPosition(position)) {
                holder.mText.text =  mCursor!!.getString(
                    mCursor!!.getColumnIndexOrThrow(AppModel.COLUMN_NAME))+"-"+
                    mCursor!!.getString(
                    mCursor!!.getColumnIndexOrThrow(AppModel.COLUMN_VERSION))
            }
        }


        override fun getItemCount(): Int {
            return if (mCursor == null) 0 else mCursor!!.count
        }

        fun setCheeses(cursor: Cursor?) {
            mCursor = cursor
            notifyDataSetChanged()
        }

        internal class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(
            android.R.layout.simple_list_item_1, parent, false)) {
            val mText: TextView = itemView.findViewById(android.R.id.text1)

        }
    }

    companion object {
        private const val LOADER_CHEESES = 1
    }
}