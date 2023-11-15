import {
  Route,
  RouterProvider,
  createBrowserRouter,
  createRoutesFromElements,
} from 'react-router-dom';
import {
  ErrorPage,
  Home, Track,
  Profile,
} from 'pages/';
import { Layout } from 'components/layout';
import './index.css';
import { library } from '@fortawesome/fontawesome-svg-core';
import { faCircleExclamation, faCircleNotch } from '@fortawesome/free-solid-svg-icons';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
library.add(faCircleExclamation, faCircleNotch);

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
    <Route path={'/profile'} element={<Layout />}>
      <Route index element={<Profile />} />
    </Route>
    <Route path={'/track'} element={<Layout />}>
      <Route index element={<Track />}/>
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
