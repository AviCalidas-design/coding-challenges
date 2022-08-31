let user;
window.onload = async () => {
    let response = await fetch("/api/session");

    let responseBody = await response.json();
    console.log("/api/reimbursement/" + responseBody.data.type);
    user = responseBody.data;
    document.getElementById("name").innerText = "Hello " + responseBody.data.firstName;
}
let modifier = document.getElementById("main-text");
var reis =[];
async function getReis(all,filtered)
{
    let response = await fetch("/api/reimbursement/" + all + "/" + filtered);

    let responseBody = await response.json();
    return responseBody.data;
}
let logoutBtn = document.getElementById("logout");
logoutBtn.addEventListener("click", () => {
    fetch("/api/session", { method: "DELETE" });
    window.location = "../login/index.html";
});
async function validate(a)
{
    let all;
    if(a == 'false')
    {
        all = false;
    }
    else if(a == 'true')
    {
        all = true;
    }
    return ((all && user.type == "manager") || (!all && user.type == "employee"));
}
async function viewYourTickets(all,filtered)
{
    if(await validate(all))
    {
    reis = await getReis(all,filtered);
    var finText = "<table style = 'width:100%;'><tr><th>ID</th><th>Name</th><th>Amount</th><th>Holder ID</th><th>Type</th><th>Status</th></tr>";
    for(let i = 0 ; i < reis.length ; i++)
    {
        checker = reis[i];
        finText += "<tr>";
        finText += "<td>" + checker.id + "</td>";
        finText += "<td>" + checker.name + "</td>";
        finText += "<td>" + checker.balance + "</td>";
        finText += "<td>" + checker.userId + "</td>";
        finText += "<td>" + checker.type + "</td>";
        finText += "<td>" + checker.status + "</td>";
        finText += "</tr>";
    }
    finText += "</table>";
    modifier.innerHTML = finText;
    modifier.style.display = "inline";
    }
    else
    {
        await forbid();
    }
}
async function forbid()
{
    modifier.innerHTML = "<p>You are not authorized to use that command</p>";
    modifier.style.display = "inline";
}
async function showApproveOrDeny()
{
    if(user.type == "manager")
    {
    reis = await getReis("true");
    var finText = "<table>";
    for(let i = 0 ; i < reis.length ; i++)
    {
        checker = reis[i];
        finText += "<tr>";
        finText += "<td>" + checker.name + "</td>";
        finText += "<td><button class = 'approve' onclick = 'authority(" + checker.id + "," + "\"approve\")'>Approve</button></td>";
        finText += "<td><button class = 'deny' onclick = 'authority(" + checker.id + "," + "\"deny\")'>Deny</button></td>";
        finText += "</tr>";
    }
    finText += "</table>";
    modifier.innerHTML = finText;
    modifier.style.display = "inline";
    }
    else
    {
        await forbid();
    }
}
async function authority(id, action)
{
    let response = await fetch("/api/reimbursement/" + id + "/" + action, {method : "PATCH"});

    let responseBody = await response.json();
    console.log(responseBody.data);
}
async function showApply()
{
    if(user.type == "employee")
    {
    modifier.innerHTML = '<form id="apply-form" class = "frm"><input type="text" id="username-input" placeholder="Name"/><input type="number" id="amount-input" placeholder="Amount"/><input type="text" id="type-input" placeholder="Type"/><button class="btn btn-success">Apply</button><br></form>';
    modifier.style.display = "inline";
    let applier = document.getElementById("apply-form");
    applier.addEventListener("submit", async (event) =>{
    event.preventDefault();
    let name = document.getElementById("username-input");
    let amount = document.getElementById("amount-input");
    let type = document.getElementById("type-input");

    let response = await fetch("/api/reimbursement", {
        method: "POST",
        body : JSON.stringify({
            "name" : name.value,
            "balance" : amount.value,
            "type" : type.value
        })
    }
    );
    let responseBody = await response.json();
    console.log(responseBody.data);
    });
    }
    else
    {
        await forbid();
    }
}