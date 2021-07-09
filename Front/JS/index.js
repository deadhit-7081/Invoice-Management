const invoice = [];
var page = 1;
var pages = 0;
var data;
const max_row = 50;
var data1;

var editinvoiceAmount="";
var editnote="";

var d = Date.now();

function button1()
{
    if(page==1)
    {
        document.getElementById("butpage1").disabled = true;
        document.getElementById("butpage1").style.backgroundColor='#39495E'
    }
    else{
        if(document.getElementById("butpage2").disabled==true)
        {
            document.getElementById("butpage2").disabled = false;
            document.getElementById("butpage2").style.backgroundColor='#14AFF1'
        }
        page=page-1;
        //////console.log(page)
        data2 = pagination(data,page,max_row);
        //////console.log(data)
        data1 = data2.data;
        //////console.log("1 ",data1)
        if(page==1)
        {
            document.getElementById("butpage1").disabled = true;
            document.getElementById("butpage1").style.backgroundColor='#39495E'
        }

        createTable(data1);
    }
}

function button2()
{
    if(page==pages)
    {
        document.getElementById("butpage2").disabled = true;
        document.getElementById("butpage2").style.backgroundColor='#39495E'
    }
    else{
        if(document.getElementById("butpage1").disabled==true)
        {
            document.getElementById("butpage1").disabled = false;
            document.getElementById("butpage1").style.backgroundColor='#14AFF1'
        }
        page=page+1;
        //////console.log(page)
        data2 = pagination(data,page,max_row);
        //////console.log(data)
        data1 = data2.data;
        //////console.log("1 ",data1)
        if(page==pages)
        {
            document.getElementById("butpage2").disabled = true;
            document.getElementById("butpage2").style.backgroundColor='#39495E'
        }

        createTable(data1);
    }
}

function pagination(query,page,max_row)
{
    var start = (page-1)*max_row;
    var end = start+max_row;

    var tdata = query.slice(start,end);
    pages = Math.ceil(query.length/max_row)

    return{
        'data':tdata,
        'pages':pages
    }
}

function createTable(data1)
{
    var thead = `<thead>
        <tr style="color: darkgrey;">
            <th><input type="checkbox" disabled="disabled"/></th>
            <th>Customer Name</th>
            <th>Customer#</th>
            <th>Invoice#</th>
            <th>Invoice Amount</th>
            <th>Due in Date</th>
            <th>Predicted Payment Date</th>
            <th>Notes</th>
        </tr>
    </thead>`
    var tbody = "<tbody>";
    for (let key in data1) {
        tbody +=`<tr>
        <td id="tr${key}${page}"><input type="checkbox" id="chk${key}" onClick="checkChkbox(this.id)" /></td>
        <td>${data1[key].name_customer}</td>
        <td>${data1[key].cust_name}</td>
        <td id="invo${key}${page}">${data1[key].invoice_id}</td>
        <td>${data1[key].total_open_amount}</td>
        <td id="due${key}${page}">${data1[key].due_in_date}</td>
        <td>${data1[key].predicted_date?data1[key].predicted_date:"-"}</td>
        <td>${data1[key].notes}</td>
        </tr>`;
    }
    tbody += "</tbody>";

    var table = "<table>"+thead+tbody+"</table>"
    document.getElementById("table").innerHTML = table;

    for (let key in data1)
    {
        let mydate = new Date(data1[key].due_in_date);
        if(mydate<d)
        {
            document.getElementById(`due${key}${page}`).style.color="#FF5B5B";
        }
    }
}

function onstart(){
    axios.get("http://localhost:8080/H2HBABBA3081/")
    .then((res)=>{
        //console.log(res.data)
        data = res.data
        //////console.log("start ",page)
        data2 = pagination(data,page,max_row);
        ////////console.log(data2)

        data1 = data2.data;
        //////console.log("1 ",data1)
        document.getElementById("butpage1").disabled = true;
        document.getElementById("butpage1").style.backgroundColor='#39495E'

       document.getElementById("buttonEdit").disabled = true;
        //document.getElementById("buttonEdit").style.backgroundColor='#97A1A9'
        document.getElementById("buttonEdit").style.borderColor='#97A1A9'

        document.getElementById("buttonDelete").disabled = true;
        //document.getElementById("buttonDelete").style.backgroundColor='#97A1A9'
        document.getElementById("buttonDelete").style.borderColor='#97A1A9'

        if(page==pages)
        {
            document.getElementById("butpage2").disabled = true;
            document.getElementById("butpage2").style.backgroundColor='#39495E'
        }

        createTable(data1);

       /* var tbody = "<tbody>";
        for (let key in data) {
            tbody += `<tr>
            <td id="tr${key}${page}"><input type="checkbox" id="chk${key}" onClick="checkChkbox(this.id)" /></td>
            <td>${data[key].name_customer}</td>
            <td>${data[key].cust_name}</td>
            <td id="invo${key}${page}">${data[key].invoice_id}</td>
            <td>${data[key].total_open_amount}</td>
            <td>${data[key].due_in_date}</td>
            <td>${data[key].predicted_date?data[key].predicted_date:"-"}</td>
            <td>${data[key].cust_payment_terms}</td>
            </tr>`;
        }
        tbody += "</tbody>";
        document.getElementById("tbody").innerHTML = tbody;*/
    })
    .catch((err)=>{
        //////console.log(err)
    })
}

function edCheckBox()
{
    if(invoice.length==1)
    {
        document.getElementById("buttonEdit").disabled = false;
        //document.getElementById("buttonEdit").style.backgroundColor='#97A1A9'
        document.getElementById("buttonEdit").style.borderColor='#14AFF1'

        document.getElementById("buttonDelete").disabled = false;
        //document.getElementById("buttonDelete").style.backgroundColor='#97A1A9'
        document.getElementById("buttonDelete").style.borderColor='#14AFF1'

        document.getElementById("buttonAdd").disabled = false;
        //document.getElementById("buttonEdit").style.backgroundColor='#97A1A9'
        document.getElementById("buttonAdd").style.borderColor='#14AFF1'

    }
    else if(invoice.length>1)
    {
        document.getElementById("buttonEdit").disabled = true;
        //document.getElementById("buttonEdit").style.backgroundColor='#97A1A9'
        document.getElementById("buttonEdit").style.borderColor='#97A1A9'

        document.getElementById("buttonAdd").disabled = true;
        //document.getElementById("buttonEdit").style.backgroundColor='#97A1A9'
        document.getElementById("buttonAdd").style.borderColor='#97A1A9'
    }
    else{
        document.getElementById("buttonEdit").disabled = true;
        //document.getElementById("buttonEdit").style.backgroundColor='#97A1A9'
        document.getElementById("buttonEdit").style.borderColor='#97A1A9'

        document.getElementById("buttonDelete").disabled = true;
        //document.getElementById("buttonDelete").style.backgroundColor='#97A1A9'
        document.getElementById("buttonDelete").style.borderColor='#97A1A9'
    }
}

function checkChkbox(id)
{
    var box = document.getElementById(id).parentElement;
    var pid = box.id;
    var box1 = document.getElementById(pid).parentElement;
    var c = box1.children[3].id;
    var cval = document.getElementById(c).innerHTML;
    ////console.log(cval)
    editinvoiceAmount = box1.children[4].innerHTML;
    if(document.getElementById(id).checked==true)
    {
        box1.style.backgroundColor = '#14AFF1';
        invoice.push(cval)
        //console.log("Add invo ",invoice)
        edCheckBox()
    }
    else{
        box1.style.backgroundColor = '#273D49CC';
        var i = invoice.indexOf(cval)
        if(i!==-1)
        {
            invoice.splice(i,1);
        }
        edCheckBox()
        //console.log("Delete invo ",invoice)
    }
}


//AddModal
var addmodal = document.getElementById('addModal');
var addModalbtn = document.getElementById('buttonAdd');
addModalbtn.addEventListener('click',openAddModal);
var addcloseModal = document.getElementById('addcloseModal');
addcloseModal.addEventListener('click',addCloseModal);

var addcancelbtn = document.getElementById('afcancelbtn');
addcancelbtn.addEventListener('click',addCloseModal)

function addCloseModal()
{
    addmodal.style.display='none';
    document.getElementById('custname').value="";
    document.getElementById('custnum').value="";
    document.getElementById('invonum').value="";
    document.getElementById('invoamt').value="";
    document.getElementById('dueindate').value="";
    document.getElementById("duplicate").innerHTML="";
    document.getElementById('note').value="";

}

function openAddModal()
{
    addmodal.style.display="block";
    document.getElementById('afaddbtn').disabled=true;
    document.getElementById('afaddbtn').style.backgroundColor='#97A1A9';
}

//Getting the value as input changes for add

var customerName="";
var customerNumber="";
var invoiceNumber="";
var invoiceAmount="";
var dueinDate="";
var note="";

function changecustName()
{
    var input1 = document.getElementById('custname').value;
    //////console.log(input1);
    customerName=input1;
    edaddbtn();
}

function changecustNo()
{
    var input1 = document.getElementById('custnum').value;
    ////console.log(input1);
    customerNumber=input1;
    edaddbtn()
}

function changeinvoNum()
{
    var input1 = document.getElementById('invonum').value;
    ////console.log(input1);
    invoiceNumber=input1;
    edaddbtn()
}

function changeinvoAmt()
{
    var input1 = document.getElementById('invoamt').value;
    ////console.log(input1);
    invoiceAmount=input1;
    edaddbtn()
}

function changedueindate()
{
    var input1 = document.getElementById('dueindate').value;
    ////console.log(input1);
    dueinDate=input1;
    edaddbtn()
}

function changeNotes()
{
    var input1 = document.getElementById('note').value;
    ////console.log(input1);
    note=input1;
}

//function to enable and disable add button
function edaddbtn()
{
    if(customerName.length!=0 && customerNumber.length!=0 && invoiceNumber.length!=0 && invoiceAmount.length!=0 && dueinDate.length!=0)
    {
        document.getElementById('afaddbtn').disabled=false;
        document.getElementById('afaddbtn').style.backgroundColor='#14AFF1';
    }
    else
    {
        if(customerName.length==0 || customerNumber.length==0 || invoiceNumber.length==0 || invoiceAmount.length==0 || dueinDate.length==0)
        {
            document.getElementById('afaddbtn').disabled=true;
            document.getElementById('afaddbtn').style.backgroundColor='#97A1A9';
        }
    }
}

var x = document.getElementById("afaddbtn").addEventListener('click',addData);

function addData(e)
{    
    sdata={
        customerName:customerName,
        customerNumber:customerNumber,
        invoiceNumber:invoiceNumber,
        invoiceAmount:invoiceAmount,
        dueinDate:dueinDate,
        note:note
    }
    e.preventDefault();
    axios.post("http://localhost:8080/H2HBABBA3081/addData",sdata,
    {
        headers:{
            "Access-Control-Request-Origin":"http://localhost:8080/H2HBABBA3081/",
            "content-type":"application/json"
        }
    }).then((res)=>{
        //console.log("Res - ",res)

        if(res.headers.duplicate==="true")
        {
            document.getElementById("duplicate").innerHTML=res.headers.data;
        }
        else{
            addCloseModal()
            onstart()
        }
    })
    .catch((err)=>{
        //console.log(err);
    })
    //console.log("Data Send - ",sdata)
}

function addClear()
{
    document.getElementById('custname').value="";
    document.getElementById('custnum').value="";
    document.getElementById('invonum').value="";
    document.getElementById('invoamt').value="";
    document.getElementById('dueindate').value="";
    document.getElementById("duplicate").innerHTML="";

    edaddbtn();
}

//Opeaning and closing edit Model

var editmodal = document.getElementById('editModal');
var editModalbtn = document.getElementById('buttonEdit');
editModalbtn.addEventListener('click',openEditModal);
var editcloseModal = document.getElementById('editcloseModal');
editcloseModal.addEventListener('click',editCloseModal);

var editcancelbtn = document.getElementById('efcancelbtn');
editcancelbtn.addEventListener('click',editCloseModal)

function editCloseModal()
{
    editmodal.style.display='none';
}

function openEditModal()
{
    document.getElementById('editinvoamt').value = editinvoiceAmount
    editmodal.style.display="block";
}

//Getting values for input change for edit

function changeeditinvoAmt()
{
    var input1 = document.getElementById('editinvoamt').value;
    ////console.log(input1);
    editinvoiceAmount=input1;
    edaddbtn()
}

function changeeditNotes()
{
    var input1 = document.getElementById('editnote').value;
    ////console.log(input1);
    editnote=input1;
}

function ededitbtn()
{
    if(editinvoiceAmount!=null)
    {
        document.getElementById('efsavebtn').disabled=false;
        document.getElementById('efsavebtn').style.backgroundColor='#14AFF1';
    }
    else if(editinvoiceAmount.length==0)
    {
        document.getElementById('efsavebtn').disabled=true;
        document.getElementById('efsavebtn').style.backgroundColor='#97A1A9';
    }
}

function editClear()
{
    document.getElementById('editinvoamt').value="";
    document.getElementById('editnote').value="";
}

var x = document.getElementById("efsavebtn").addEventListener('click',editData);

function editData(e)
{   
    sdata={
        invoiceNumber:invoice[0],
        invoiceAmount:editinvoiceAmount,
        note:editnote
    }
    e.preventDefault();

    //console.log("Edit - ",sdata)
    axios.post("http://localhost:8080/H2HBABBA3081/editData",sdata,
    {
        headers:{
            "Access-Control-Request-Origin":"http://localhost:8080/H2HBABBA3081/",
            "content-type":"application/json"
        }
    }).then((res)=>{
        //console.log("Res Edit - ",res)
        editCloseModal()
        location.reload()
    })
    .catch((err)=>{
        //console.log(err);
    })
    //console.log("Data Send Edit - ",sdata)
}

//Opeaning and closing of the delete Modal

var deletemodal = document.getElementById('deleteModal');
var deleteModalbtn = document.getElementById('buttonDelete');
deleteModalbtn.addEventListener('click',openDeleteModal);
var deletecloseModal = document.getElementById('deletecloseModal');
deletecloseModal.addEventListener('click',deleteCloseModal);

var deletecancelbtn = document.getElementById('dfcancelbtn');
deletecancelbtn.addEventListener('click',deleteCloseModal)

function deleteCloseModal()
{
    deletemodal.style.display='none';
}

function openDeleteModal()
{
    deletemodal.style.display="block";
}

var x = document.getElementById("dfdeletebtn").addEventListener('click',deleteData);

function deleteData(e)
{    
    sdata={
        invoiceNumber:invoice
    }
    e.preventDefault();

    //console.log("Edit - ",sdata)
    axios.post("http://localhost:8080/H2HBABBA3081/deleteData",sdata,
    {
        headers:{
            "Access-Control-Request-Origin":"http://localhost:8080/H2HBABBA3081/",
            "content-type":"application/json"
        }
    }).then((res)=>{
        //console.log("Res delete - ",res)
        deleteCloseModal()
        location.reload()
    })
    .catch((err)=>{
        //console.log(err);
    })
    //console.log("Data Send delete - ",sdata)
}


//Search Invoice

var searchinvo="";

var x = document.getElementById("searchInvo").addEventListener('keydown',function(e)
{
    if(e.code==='Enter')
    {
        searchinvo = e.target.value;
        searchData(e);
    }
});

function searchData(e)
{    
    sdata={
        invoiceNumber:searchinvo
    }
    e.preventDefault();

    //console.log("Search - ",sdata)
    axios.post("http://localhost:8080/H2HBABBA3081/searchData",sdata,
    {
        headers:{
            "Access-Control-Request-Origin":"http://localhost:8080/H2HBABBA3081/",
            "content-type":"application/json"
        }
    }).then((res)=>{
        //console.log("Res Search - ",res.data)
        //console.log("Length of Res ",res.data.length)

        if(res.data.length==0)
        {
            var body = "<table>";

            body+=`<p style="color:white">
                <img id="nores" src="../images//error_outline-24px.png" alt="err"/>
                <p id="para"><span id="para2">No results found<span><br/><br/>
                Try adjusting your search to find what you are looking for.<br/><br/>
                <button id="clearBtn" onclick="clearSearchBox()">Clear Search</button></p>
            </p>`
            body += "</table>";
            document.getElementById("table").innerHTML = body;
            document.getElementById("para").style.color="white"
            document.getElementById("para").style.textAlign="center"
            document.getElementById("para2").style.fontSize="110%"
            document.getElementById("nores").style.marginTop="15%"
            document.getElementById("nores").style.marginLeft="48%"
            document.getElementById("clearBtn").style.backgroundColor="#273D49CC"
            document.getElementById("clearBtn").style.color="#14AFF1"
            document.getElementById("clearBtn").style.border="none"
            document.getElementById("clearBtn").style.cursor="pointer"
            
            //document.getElementById("para2").style.marginLeft="35%"
            //document.getElementById("para2").style.color="white"
        }
        else{
            createTable(res.data);
        }
    })
    .catch((err)=>{
        //console.log(err);
    })
    //console.log("Data Send Search - ",sdata) 
}

function clearSearchBox()
{
    document.getElementById("searchInvo").value = "";
    onstart()
}

/*Modal

var Modal = document.getElementById("simpleModal");
var modalBut = document.getElementById('modalBut');
var closeBtn = document.getElementsByClassName('closeBtn')[0];
////console.log(modalBut)

modalBut.addEventListener('click',openModal);

window.addEventListener('click',outsideClick)

//func openModal

function openModal()
{
    ////console.log(modalBut)
    Modal.style.display='block'
}

closeBtn.addEventListener('click',closeModal);
function closeModal()
{
    ////console.log(closeBtn)
    Modal.style.display='none'
}

/*function outsideClick(e)
{
    if(e.target==Modal)
    {
        Modal.style.display='none'
    }
}*/