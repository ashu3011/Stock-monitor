<html>
<head>
<script>

function addCompany() {
	var cname = document.getElementById("addcname").value;
	document.getElementById("myForm").action ="webapi/companies/add/" + cname;
	document.getElementById("myForm").method = "post";
	document.getElementById("myForm").submit();
}
function delCompany() {
	var cname = document.getElementById("delcom").value;
	document.getElementById("myForm").action ="webapi/companies/delete/" + cname;
	document.getElementById("myForm").method = "post";
	document.getElementById("myForm").submit();
}
function getHistory() {
	var cname = document.getElementById("history").value;
	document.getElementById("myForm").action ="webapi/companies/history/" + cname;
	document.getElementById("myForm").method = "get";
	document.getElementById("myForm").submit();
}

</script>

</head>
<body>
    <h1>Stock Monitoring API</h2>
    <ol>
    <li>
    <h2><a href="webapi/companies/list">List all companies in the database</a>
    </li>
    <li>
    <h2>Add Company</h2></a>
    <input id="addcname" type = 'text' placeholder="Stock Symbol"> <button onclick="addCompany()"> Add </button>
    <form  id="myForm" name="myForm" action="webapi/companies/list" method="post">
	</form>
    </li>
    <li>
    <h2>Delete Company</h2>
    <input  id="delcom" type = 'text' placeholder="Stock Symbol"> <button onclick="delCompany()"> Delete </button>
    </li>
    <li>
    <h2>Company History</h2>
    <input id="history" type = 'text' placeholder="Stock Symbol"> <button onClick="getHistory()"> Get History </button>
    </li>
    </ol>
</body>
</html>
