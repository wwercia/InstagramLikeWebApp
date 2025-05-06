function addCollaborator() {
    const container = document.getElementById('collaborators');
    const label = document.createElement('label');
    label.innerHTML = `Collaborator: <input type="text" name="collaborators[]" required>`;
    container.appendChild(document.createElement('br'));
    container.appendChild(label);
}