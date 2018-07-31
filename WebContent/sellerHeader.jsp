
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
            
            		<li class="nav-item">
              <a class="nav-link" href="/CSE305/ItemController?command=getSellerItems">MY Items</a>
            		</li>
            		
            		<li class="nav-item">
              <a class="nav-link" href="UserAccountController">My Account</a>
            		</li>
            		
            		<li class="nav-item">
              <a class="nav-link" href="sellerPaymentsController">My Payments</a>
            		</li>
          

          </ul>
        </div>
        
        		
      </div>
    </nav>