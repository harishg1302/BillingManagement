<style>
	#allUsersTbl {
		font-family: Arial, Helvetica, sans-serif;
		border-collapse: collapse;
		width: 100%;
	}

	#allUsersTbl td, #allUsersTbl th {
		border: 1px solid #ddd;
		padding: 8px;
	}

	#allUsersTbl tr:nth-child(even) {
		background-color: #f2f2f2;
	}

	#allUsersTbl tr:hover {
		background-color: #ddd;
	}

	#allUsersTbl th {
		padding-top: 12px;
		padding-bottom: 12px;
		text-align: left;
		background-color: #04AA6D;
		color: white;
	}

	.button {
		position: absolute;
		top: 50%;
		background-color: #0a0a23;
		color: #fff;
		border: none;
		border-radius: 10px;
		padding: 15px;
		min-height: 30px;
		min-width: 120px;
	}

	button:hover {
		background-color: #04AA6D;
	}
</style>
<table id="allUsersTbl">
	<thead>
	<th>S. No.</th>
	<th>Name</th>
	<th>Email Id</th>
	<th>Action</th>
	</thead>
	<tbody id="allUsersTBody">

	</tbody>
</table>
