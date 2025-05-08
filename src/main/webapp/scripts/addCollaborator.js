function addCollaborator() {
    const container = document.getElementById('collaborators');
    const existingCollaborators = container.querySelectorAll('label').length;

    if (existingCollaborators >= 10) {
        document.getElementById('collaboratorLimitMessage').style.display = 'block';
        document.getElementById('addCollaboratorButton').disabled = true;
        return;
    }

    const label = document.createElement('label');
    label.className = 'collaborator-label';
    label.innerHTML = `Collaborator: <input type="text" name="collaborators[]" required id="collaborator-input">`;

    container.appendChild(label);
}