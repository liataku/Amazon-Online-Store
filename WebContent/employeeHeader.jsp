
<c:url var="logoutLink" value="LoginController">
<c:param name="logOut" value="true" />
-					</c:url>
		
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        <a class="navbar-brand" href="http://localhost:8080/CSE305">Home</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
          		<li class="nav-item">
          		
			    		<form action="ItemController" class="form-control" method="get">
        
                			<input type="hidden" name="command" value="search" />
           				<input type="text" name="search" placeholder="Search for..." />
						<input type="submit" value="Search" class="add-student-button" />
            			</form>
  					
          		</li>
            
            	<li class="nav-item">
              <a class="nav-link" href= "${logoutLink}" >Logout</a>
            </li>
            	<li class = "nav-item">
            	              <div class="dropdown">
 							 <button class="btn btn-secondary btn-sm dropdown-toggle dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
   								 Database
 								 </button>
 								 <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
   								 <a class="dropdown-item" href="EmployeeController?command=listUsers">Edit Users</a>
   								 <a class="dropdown-item" href="EmployeeController?command=listItems">Edit Items</a>
  							
 								 </div>
							</div>
            		</li>
            		<li class="nav-item">
              <a class="nav-link" href="#">My Account</a>
            		</li>
          

          </ul>
        </div>
        
        		
      </div>
    </nav>