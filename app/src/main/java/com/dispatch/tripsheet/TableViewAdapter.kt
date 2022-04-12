package com.dispatch.tripsheet




    import android.util.Log
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.Button
    import android.widget.TextView
    import androidx.recyclerview.widget.RecyclerView
    import kotlinx.android.synthetic.main.table_list_item.view.*

class TableViewAdapter( var Tripsheetlist: List<DataModel> = emptyList()) : RecyclerView.Adapter<TableViewAdapter.RowViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.table_list_item, parent, false)
        return RowViewHolder(itemView)  }

    override fun getItemCount(): Int { return Tripsheetlist.size + 1 }


    private  fun updateData(data: List<DataModel>) {
        Tripsheetlist = data
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RowViewHolder, position: Int) {

        val rowPos = holder.adapterPosition

        if (rowPos == 0) {
            // Header Cells. Main Headings appear here
            holder.itemView.apply {
                setHeaderBg(txtWOrder)
                setHeaderBg(txtDElNote)
                setHeaderBg(txtCompany)
                setHeaderBg(txtWeight)
//               setHeaderBg(txtbutton1)
//                setHeaderBg(txtbutton2)
//                setHeaderBg(txttvdone)


                txtWOrder.text = "WOrder"
                txtDElNote.text = "DElNote"
                txtCompany.text = "Company"
                txtWeight.text = "Weight"
//                txtbutton1.text = "Delivered"
//                txtbutton2.text = "Exception"
//                txttvdone.text = ""
            }
        } else {
            val modal = Tripsheetlist[rowPos  -1]

            holder.itemView.apply {
                setContentBg(txtWOrder)
                setContentBg(txtDElNote)
                setContentBg(txtCompany)
                setContentBg(txtWeight)
                setContentBg(txtbutton1)
                setContentBg(txtbutton2)
                setContentBg(txttvdone)

                txtWOrder.text = modal.WOrder.toString()
                txtDElNote.text = modal.DElNote.toString()
                txtCompany.text = modal.Company.toString()
                txtWeight.text = modal.Weight.toString()
                txtbutton1.text = modal.Button1.toString()
                txtbutton2.text = modal.Button2.toString()
                txttvdone.text = modal.tvdone.toString()
            }
        }



        if ( rowPos  != 0){
            val modal =Tripsheetlist[rowPos -1 ]
            println("rowPos")
println(rowPos)
            println(modal.state)


        holder.txttvdone.apply {
            setBackgroundResource(when (modal.state) { // tripsheet[4].state
                DataState.Unselected -> android.R.color.transparent
                DataState.Success -> R.color.green
                DataState.Failure -> R.color.orange
            })
            text = when (modal.state) {
                DataState.Unselected -> ""
                DataState.Success -> "✓"
                DataState.Failure -> "x"
                //this is where I add code to export data through api maybe add it in the datastate set where it is success and Failure
            }
        }

        holder.apply {
            txtbutton1.setOnClickListener {
                Log.e("Clicked", "Successful delivery")
                //this is where I add code to export data through api
                modal.state = DataState.Success
                notifyDataSetChanged()
            }
            txtbutton2.setOnClickListener {
                Log.e("Clicked", "Exception on delivery")

                modal.state = DataState.Failure
                notifyDataSetChanged()
            }
        }}

    }
     class RowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

         val txttvdone:TextView = itemView.findViewById<TextView>(R.id.txttvdone)
         val txtbutton1:Button = itemView.findViewById<Button>(R.id.txtbutton1)
         val txtbutton2:Button = itemView.findViewById<Button>(R.id.txtbutton2)
     }


    //not used = same as RowView Holder
//    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
//
//        var txtbutton1 = view.findViewById<Button>(R.id.txtbutton1)
//        val txtbutton2:Button = itemView.findViewById<Button>(R.id.txtbutton2)
//        var txttvdone = view.findViewById<TextView>(R.id.txttvdone)
//    }


    private fun setHeaderBg(view: View) {
        view.setBackgroundResource(R.drawable.table_header_cell_bg)
    }

    private fun setContentBg(view: View) {
        view.setBackgroundResource(R.drawable.table_content_cell_bg)
    }



}