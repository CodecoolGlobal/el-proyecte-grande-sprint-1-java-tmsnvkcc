import {
  Route,
  RouterProvider,
  createBrowserRouter,
  createRoutesFromElements,
} from 'react-router-dom';
import {
  QueryClient,
  QueryClientProvider,
} from '@tanstack/react-query';
import {
  Dashboard,
  ErrorPage,
  Home,
  Insights,
  Profile,
  Track,
} from 'pages';
import { Layout } from 'components/layout';
import './index.css';
import { library } from '@fortawesome/fontawesome-svg-core';
import { faCircleExclamation, faCircleNotch, faEye, faEyeSlash } from '@fortawesome/free-solid-svg-icons';
library.add(faCircleExclamation, faCircleNotch, faEye, faEyeSlash);

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      enabled: true,
      refetchOnWindowFocus: false,
      staleTime: Infinity,
    },
  },
});

const router = createBrowserRouter(createRoutesFromElements(
  <Route errorElement={<ErrorPage />}>
    <Route path={'/'}>
      <Route index element={<Home />} />
    </Route>
    <Route path={'/dashboard'} element={<Layout />}>
      <Route index element={<Dashboard />} />
    </Route>
    <Route path={'/track'} element={<Layout />}>
      <Route index element={<Track />} />
    </Route>
    <Route path={'/insights'} element={<Layout />}>
      <Route index element={<Insights />} />
    </Route>
    <Route path={'/profile'} element={<Layout />}>
      <Route index element={<Profile />} />
    </Route>
  </Route>,
));

const Application = () => {
  return (
    <QueryClientProvider client={queryClient}>
      <RouterProvider router={router} />
    </QueryClientProvider>
  );
};

export default Application;
