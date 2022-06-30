import { BrowserRouter, Routes, Route } from 'react-router-dom';
import LoginPage from './pages/LoginPage';
import OauthCallback from './pages/OauthCallback';
import SearchPage from './pages/SearchPage';
import UserPage from './pages/UserPage';

function App() {

    return (
        <div>
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<LoginPage />} />
                    <Route path="/user/:username" element={<UserPage />} />
                    <Route path="/search/:username" element={<SearchPage />} />
                    <Route path="/oauth" element={<OauthCallback />} />
                </Routes>
            </BrowserRouter>
        </div>
    );
}

export default App;
