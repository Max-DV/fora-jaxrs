$.get('http://localhost:8080/jax-rs-1/fora/users')
    .then(users=> users.map(u=>u.name))
    .then(names=> names.forEach(name=> $("section.users ul")
        .append("<li>"+name+"</li>")))



