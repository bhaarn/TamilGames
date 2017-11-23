git checkout -b release master    # create and switch to the release branch
git push -u origin release        # push the release branch to the remote and track it
git branch -d master              # delete local master
git push --delete origin master   # delete remote master
git remote prune origin           # delete the remote tracking branch
