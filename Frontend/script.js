function showToast(text) {
    let t = document.getElementById("toast");
    t.innerText = text;
    t.className = "show";
    setTimeout(() => t.className = t.className.replace("show", ""), 2500);
}

document.getElementById("myform").addEventListener("submit", async (e)=>{
    e.preventDefault();

    let name = document.getElementById("nm").value;
    let roll = document.getElementById("rl").value;
    let grade = document.getElementById("gr").value;

    if(name.trim() == "" || roll.trim == "" || grade.trim() == "") {
        showToast("Please fill all fields");
        return;
    }

    await fetch("http://localhost:8000/add", {
        method: "POST",
        headers: {"Content-Type":"application/x-www-form-urlencoded"},
        body: "name="+name+"&roll="+roll+"&grade="+grade
    });

    document.getElementById("nm").value = "";
    document.getElementById("rl").value = "";
    document.getElementById("gr").value = "";

    showToast("Student Added");
    loadData();
});

async function loadData() {
    let res = await fetch("http://localhost:8000/students");
    let data = await res.json();

    let tb = document.querySelector("#tbl tbody");
    tb.innerHTML = "";

    data.forEach(s => {
    tb.innerHTML += `
        <tr>
            <td>${s.name}</td>
            <td>${s.roll}</td>
            <td>${s.grade}</td>
            <td><button onclick="del(${s.roll})" style="background:red; padding:5px 10px;">Delete</button></td>
        </tr>`;
});


    document.getElementById("count").innerText = "Total Students: " + data.length;
}

async function del(roll) {
    await fetch("http://localhost:8000/delete?roll="+roll);
    showToast("Student Deleted");
    loadData();
}

function filterTable() {
    let key = document.getElementById("search").value.toLowerCase();
    let rows = document.querySelectorAll("#tbl tbody tr");

    rows.forEach(r => {
        let txt = r.innerText.toLowerCase();
        r.style.display = txt.includes(key) ? "" : "none";
    });
}

loadData();
