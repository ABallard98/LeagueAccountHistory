const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

class App extends React.Component{
    constructor(props) {
        super(props);
        this.state = {leagueAccounts: []};
    }
    componentDidMount() {
        client({
            method: 'GET',
            path: '/api/leagueAccounts'
        }).done(response => {
            this.setState({
                leagueAccounts: response.entity._embedded.leagueAccounts
            });
        });
    }
    render(){
        return (<LeagueAccountList leagueAccounts={this.state.leagueAccounts}/>);
    }
}

class LeagueAccountList extends React.Component{
    render(){
        const leagueAccounts = this.props.leagueAccounts.map(leagueAccount =>
            <LeagueAccount key={leagueAccount._links.self.href} leagueAccount={leagueAccount}/>);
        return(
            <table>
                <tbody>
                    <tr>
                        <th>Region ID</th>
                        <th>Summoner ID</th>
                        <th>Account Name</th>
                        <th>Summoner Level</th>
                        <th>Profile Pic ID</th>
                        <th>Main Champion</th>
                    </tr>
                    {leagueAccounts}
                </tbody>
            </table>
        )
    }
}

class LeagueAccount extends React.Component{
    render(){
        return(
            <tr>
                <td>{this.props.leagueAccount.regionId}</td>
                <td>{this.props.leagueAccount.summonerId}</td>
                <td>{this.props.leagueAccount.accountName}</td>
                <td>{this.props.leagueAccount.summonerLevel}</td>
                <td>{this.props.leagueAccount.profilePicId}</td>
                <td>{this.props.leagueAccount.mainChampion}</td>
            </tr>
        )
    }
}

ReactDOM.render(
    <App/>,
    document.getElementById('react')
)

